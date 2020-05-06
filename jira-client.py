#!/usr/bin/env python3

import logging
from jira import JIRA
import fire


class JiraClient(object):
    __jira_options = {'server': 'https://jira.kaaproject.org'}
    __jira = None

    def __init__(self, login="", password="", jira_server="https://jira.kaaproject.org"):
        logging.basicConfig(
            level=logging.INFO,
            format='%(asctime)s %(message)s'
        )
        try:
            self.__jira = JIRA(options=self.__jira_options, basic_auth=(login, password))
        except Exception as e:
            pass
        if self.__jira is None:
            logging.info('Authenticate error on server:{} login: {}'.format(self.__jira_options.get('server'), login))

    def check_issue(self, issue_key):
        try:
            if self.__jira is not None:
                issue = self.__jira.issue(issue_key)
                logging.info('issue {}, id {}, link: {}'.format(issue.key, issue.id, issue.permalink()))
                return 1    # success
        except Exception as e:
            logging.info('Not issue {} on server:{}'.format(issue_key, self.__jira_options.get('server')))
        return 0       # failure


if __name__ == '__main__':
    fire.Fire(JiraClient)


