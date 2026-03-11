# Jira Sprint Automation Lab

A practical portfolio project for Jira administration interviews:
hands-on automation with **Jira Automation (Cloud)** and **ScriptRunner (Server/Data Center)**.

## Project goal
Reduce manual work in sprint operations:
- faster sprint kickoff
- cleaner sprint end closeout
- consistent sprint health signals
- less WIP leak into new sprints

The project demonstrates real admin workflows: process design + automation rule building + scripts + audit evidence.

## Repository structure
- `automations/`
  - `jira-automation-rules.md`: ready-to-build Jira Automation rules for sprint lifecycle.
  - `jql-library.md`: JQL queries used by the automation rules.
- `scriptrunner/listeners/`
  - Groovy listeners used by ScriptRunner.
- `scriptrunner/jobs/`
  - Scheduled job scripts for periodic health checks.
- `docs/`
  - ADR and implementation notes for interview talking points.

## Scenario covered
Team uses one Scrum board in Jira with around 40 issues/sprint.

Pain points (before):
- Sprint start: no one checks whether every story has Story Points and Sprint Goal.
- Sprint close: unresolved blockers are often forgotten.
- Ongoing risk: issue with no assignee, no due date, or no comment when blocked stays hidden.

This project automates those steps end-to-end.

## What you can show to HR/recruiter
- A written process that maps rules to outcomes.
- Practical scripts that touch real Jira objects.
- Clear audit logs and measurable improvements.

## Quick setup (local docs)
1. Create a `jira` board project and set:
   - custom field "Sprint Goal" (text)
   - custom field "Story Points"
   - component/labels as needed
2. Copy values from `docs/field-mapping.md` into ScriptRunner and Automation rules.
3. Build three automation rules first (in `automations/jira-automation-rules.md`).
4. Add two listeners + one scheduled job scripts in ScriptRunner.
5. Run dry-run in staging for one sprint, then enable in production.

## Resume one-liner ready to paste
Built an end-to-end Jira sprint operations automation project using Jira Automation + ScriptRunner: delivered 3 automation rules and 3 Groovy scripts to standardize sprint kickoff, sprint health checks, and sprint closeout. Reduced manual sprint admin overhead by enforcing Story Point and Blocker checks, auto-flagging risk items, and pre-processing carryover work before retrospectives.

## Suggested GitHub project link description
`A lightweight but production-style Jira automation toolkit for Scrum teams: combines Automation for Jira rules and ScriptRunner Groovy scripts to streamline sprint planning, issue quality gates, and closeout hygiene.`
