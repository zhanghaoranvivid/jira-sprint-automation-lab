# Jira Automation for Jira (rule pack)

This pack is designed for Jira Cloud automation rules.

## Rule 1: Sprint kickoff quality gate
- Name: `Sprint Kickoff - Validate Scope Completeness`
- Trigger: `Sprint started` on project boards
- Conditions:
  - Sprint contains issues with:
    - missing Story Points
    - missing Sprint Goal (on Story/Epic only)
- Actions:
  1. Lookup issues in the sprint with JQL filter.
  2. Set label `risk-scope-missing` on each hit issue.
  3. Add comment to issue: `⚠ Sprint gate not passed: missing Story Points or Sprint Goal`.
  4. Send notification to board lead channel:
     `Sprint {{sprint.name}} started with {{issue.count}} scope gaps.`
  5. Reassign issue status (optional): move to `Need More Detail`.

JQL used:
`sprint = {{sprint.id}} AND (\"Story Points\" is EMPTY OR (type in (Story, Epic) AND \"Sprint Goal\" is EMPTY))`

## Rule 2: In-sprint blocked item escalation
- Name: `Sprint Escalation - Blocked and untouched`
- Trigger: `Issue updated`
- Conditions:
  - Issue in active sprint
  - Label = `Blocked`
  - status in (`In Progress`, `To Do`)
  - last comment not updated in last 24h
- Actions:
  1. Add label `needs-esk` (needs escalation)
  2. Add comment: `This issue has been blocked for >24h. Escalate in daily sync.`
  3. Send notification to team lead.

JQL used (for reporting only):
`status in ("In Progress", "To Do") AND labels = Blocked AND sprint in openSprints() AND updated <= -1d`

## Rule 3: Sprint closeout assistant
- Name: `Sprint Closeout - Auto carryover list`
- Trigger: `Sprint completed`
- Conditions:
  - Excludes already `Done`.
- Actions:
  1. Query all issues in completed sprint.
  2. Create Confluence page (or comment in summary issue) with:
     - unresolved count
     - blocked count
     - top 3 owners by unresolved volume
  3. Add label `sprint-close-review-required` to unresolved issues.
  4. Add comment: `Moved to carryover list on closeout.`

JQL used:
`sprint in closedSprints() AND statusCategory != Done`

## Export/Import note
- These are rule blueprints, not raw JSON.  
- Keep them as "playbooks" in repo, and when asked in interview, explain how you translated each business rule to condition/actions.

