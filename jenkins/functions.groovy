  /**
   * @NonCPS
   */
  def verifyIsIssueExists(String branchName , String username , String password , String server ) {
    String issue = parseIssueFromBranch(branchName)
    def isExist = false
    print(issue)
    if (issue == ""){
      println "NOT issue in branch ${branchName}" 
    } else {
      this.steps.sh "curl -s -o content.json -w '%{http_code}' -u ${LOGIN}:${PASSWORD} -X GET -H 'Content-Type: application/json' ${SERVER}/rest/api/2/issue/{${issue}} > status.txt"
      def props = this.steps.readJSON file: 'content.json'
      println props.get("key")
      println props.get("errorMessages")
      if (props.get("key")) {// ! = null
        print("issue (${issue}) in branch (${branchName}) IS OK")
        isExist = true
      }
      if (props.get("errorMessages"))
        print "NOT issue (${issue}) in branch (${branchName})"
    }
    return isExist
  }

  def parseIssueFromBranch(String branchName) {
    String issue = ""
    def pattern = ~/^[A-Z]{3,}\-[0-9]{1,}/ 
    def matcher = pattern.matcher(branchName)
    if( matcher.find() )
      issue = matcher[0]
    return issue
  }
