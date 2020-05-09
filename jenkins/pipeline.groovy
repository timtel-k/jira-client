@Library('jbt-shared-lib@jira-check') _

import com.jbt.jenkins.Container

def LIB = Container.builder(this)
LIB.init()

pipeline {
  agent any
  parameters {
      string(
        name: 'SERVER', 
        defaultValue: 'https://jira.kaaproject.org', 
        description: 'Jira-server link'
      )
      string(
        name: 'LOGIN', 
        defaultValue: 'test', 
        description: 'login to Jira-server'
      )
      password(
        name: 'PASSWORD', 
        defaultValue: 'test', 
        description: 'Enter a password'
      )
      string(
        name: 'BRANCH', 
        defaultValue: 'KAA-1755', 
        description: 'Enter a password'
      )
  }  
  stages {
    stage ('test') {
      steps {
        script {
            withCredentials([
                usernamePassword(
                credentialsId: '37312256-9b62-4766-a71c-ae67db031080',
                usernameVariable: 'JIRA_USERNAME',
                passwordVariable: 'JIRA_PASSWORD'
            )
            ]) {
                LIB.build.verifyIsIssueExists(BRANCH, env.JIRA_USERNAME, env.JIRA_PASSWORD, SERVER)
            }
        }
      }
    }
  }
}

