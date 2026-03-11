# JQL library for sprint ops

## Kickoff checks
- Missing story points in active sprint:
`sprint in openSprints() AND \"Story Points\" is EMPTY`

- Sprint goal missing for story/epic:
`sprint in openSprints() AND type in (Story, Epic) AND \"Sprint Goal\" is EMPTY`

## Blocker and risk checks
- Blocked & no recent comment:
`status in ("In Progress", "To Do") AND labels in (Blocked) AND sprint in openSprints() AND updated <= -1d`

- No assignee in active sprint:
`sprint in openSprints() AND assignee is EMPTY`

- No due date in active sprint:
`sprint in openSprints() AND duedate is EMPTY`

## Closeout checks
- Unresolved issues in closed sprint:
`sprint in closedSprints() AND statusCategory != Done`

- Regressed blockers after sprint close:
`sprint in closedSprints() AND labels in (Blocked, needs-esk)`

