// Scheduled Job (ScriptRunner Scheduled Jobs)
// Runs every 30 minutes during working hours
// Produce a simple risk snapshot for the active sprint

import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.search.SearchProvider
import com.atlassian.jira.web.bean.PagerFilter
import com.onresolve.scriptrunner.canned.jira.utils.JiraUtils

def user = ComponentAccessor.jiraAuthenticationContext.loggedInUser
def searchProvider = ComponentAccessor.getComponent(SearchProvider)
def parseQuery = ComponentAccessor.getComponent(com.atlassian.jira.jql.parser.JqlQueryParser)
def queryBuilder = ComponentAccessor.getComponent(com.atlassian.jira.jql.builder.JqlQueryBuilder)

String jql = "sprint in openSprints() AND (labels in (Blocked, risk-no-story-points, risk-no-sprint-goal) OR assignee is EMPTY OR duedate is EMPTY)"
def query = parseQuery.parseQuery(jql)
def results = searchProvider.search(query, user, PagerFilter.getUnlimitedFilter())

def blockedCount = 0
def noAssigneeCount = 0
def noDueDateCount = 0
def noStoryPointCount = 0
def carryoverRisk = []

results.results.each { issue ->
    if (issue.labels?.collect { it.name }.any { it in ["Blocked","risk-no-story-points","risk-no-sprint-goal"] }) {
        blockedCount++
    }
    if (!issue.assigneeId) {
        noAssigneeCount++
    }
    if (!issue.dueDate) {
        noDueDateCount++
    }
    if (!issue.customFieldValues.any { it.customField?.name == "Story Points" && it.getValue() != null }) {
        noStoryPointCount++
    }
    if (!issue.status?.statusCategory?.key.equalsIgnoreCase("done")) {
        carryoverRisk << issue.key
    }
}

def msg = """Sprint Health Snapshot:
- Total risk issues: ${results.total}
- Blocked/label-risk issues: ${blockedCount}
- No assignee: ${noAssigneeCount}
- No due date: ${noDueDateCount}
- No story points: ${noStoryPointCount}
- Top carryover risk issues: ${(carryoverRisk.take(10)).join(", ")}
"""

log.info(msg)

