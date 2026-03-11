// Listener script (ScriptRunner Listener)
// Trigger: Issue Updated OR Issue Created
// Goal:
// 1) In-open sprint issues without Story Points => add risk label
// 2) For Story/Epic without Sprint Goal => add risk label

import com.atlassian.jira.component.ComponentAccessor

def issue = issue
if (!issue) return

def cfManager = ComponentAccessor.customFieldManager
def labelManager = ComponentAccessor.labelManager
def issueManager = ComponentAccessor.issueManager
def commentManager = ComponentAccessor.commentManager
def user = ComponentAccessor.jiraAuthenticationContext.loggedInUser

def storyPointsCf = cfManager.getCustomFieldObjectByName("Story Points")
def sprintGoalCf = cfManager.getCustomFieldObjectByName("Sprint Goal")
def sprintCf = cfManager.getCustomFieldObjectByName("Sprint")

def storyPoints = issue.getCustomFieldValue(storyPointsCf)
def sprintGoal = issue.getCustomFieldValue(sprintGoalCf)
def sprintVal = issue.getCustomFieldValue(sprintCf) as String
def issueType = issue.issueType.name

def isInOpenSprint = sprintVal && sprintVal.toLowerCase().contains("active")
if (!isInOpenSprint) return

def labels = issue.labels*.toString() as Set
def needUpdate = false

if (storyPoints == null) {
    labels << "risk-no-story-points"
    needUpdate = true
}

if ((issueType == "Story" || issueType == "Epic") && (sprintGoal == null || sprintGoal.toString().trim().isEmpty())) {
    labels << "risk-no-sprint-goal"
    needUpdate = true
}

if (needUpdate) {
    labels.each { labelManager.addLabel(user, issue.id, it as String, false) }
    commentManager.create(issue, user, "Sprint quality gate: scope missing Story Points and/or Sprint Goal. Labels added automatically for triage.", false)
}
