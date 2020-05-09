# jira-client

Script to check jira issue
https://jira.readthedocs.io/en/master/examples.html#issues 

## Prerequisites
```
pip3 install -r requirements.txt
```

## How to run
define `login` / `password` and run script

```bash
JIRA_L=<login> 
JIRA_P=<password>
./jira-client.py check-issue KAA-1755 --password=$(echo $JIRA_P) --login=$(echo $JIRA_L) --jira-server=https://jira.kaaproject.org
```
