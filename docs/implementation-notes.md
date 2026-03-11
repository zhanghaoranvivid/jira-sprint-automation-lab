# Implementation notes

## Tech choices
- Jira Automation (Cloud): for low-code operations, channel notifications, and board-level triggers.
- ScriptRunner (DC/DC Cloud on DC style scripts): for cross-rule custom logic that needs Jira API access.

## What was intentionally automated
1. Sprint kickoff quality gate
   - Missing Story Points
   - Missing Sprint Goal on Story/Epic
2. Sprint closeout hygiene
   - Carryover labeling on unresolved issues
3. Sprint health jobs
   - Periodic WIP-risk scan (Blocked / missing assignee / no due date / missing story points)

## What to say in interview
- "I don't only create rules; I design governance checkpoints: input validation -> risk tagging -> escalation -> closeout."
- "I keep automation idempotent to avoid spam and duplicate comments."
- "Every rule has a manual override path (status/comment) if business priorities change."

## Evidence package (you can show screenshot + logs)
- JQL result count before/after one sprint
- Audit log from Automation rules
- Screenshot: sprint closeout summary comment
- ScriptRunner execution log for scheduled job

