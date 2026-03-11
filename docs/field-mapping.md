# Field mapping

The scripts assume these Jira fields exist:
- `Story Points` (Number) -> required for sprint planning quality gate
- `Sprint Goal` (Text) -> optional custom field on Story/Epic
- `Sprint` (Scrum Sprint) -> default Jira field

Labels used:
- `risk-no-story-points`
- `risk-no-sprint-goal`
- `needs-esk`
- `carryover`
- `Blocked`

When your board differs, only change:
- story points field name
- sprint goal field name
- status names in condition checks

No custom plugins are required besides Jira Automation and ScriptRunner.

