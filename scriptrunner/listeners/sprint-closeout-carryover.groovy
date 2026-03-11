// Listener script (ScriptRunner Listener)
// Trigger: Sprint started / Issue moved
// Goal:
// 1) If issue moved into closed sprint while not Done, keep risk label
// 2) Add comment with reason and expected handling owner

import com.atlassian.jira.component.ComponentAccessor

def issue = issue
if (!issue) return

def sprintCf = ComponentAccessor.customFieldManager.getCustomFieldObjectByName("Sprint")
def sprintVal = issue.getCustomFieldValue(sprintCf) as String

if (!sprintVal || !sprintVal.toLowerCase().contains("closed")) return
if (issue.status?.statusCategory?.key == "done") return

def labelManager = ComponentAccessor.labelManager
def commentManager = ComponentAccessor.commentManager
def user = ComponentAccessor.jiraAuthenticationContext.loggedInUser

labelManager.addLabel(user, issue.id, "carryover", false)
def msg = "Sprint closeout carryover detected. Please move this issue to the next Sprint planning scope with acceptance context."
commentManager.create(issue, user, msg, false)

