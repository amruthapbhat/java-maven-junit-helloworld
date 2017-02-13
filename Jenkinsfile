properties(
    [
        [
            $class: 'BuildDiscarderProperty',
            strategy: [$class: 'LogRotator', numToKeepStr: '10']
        ],
        pipelineTriggers([cron('H * * * *')]),
    ]
)


node {
   def mvnHome
   def scannerHome
   
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'ca9b112d-19c3-491c-8e6d-23ec20cc5290', refspec: '+refs/pull/*:refs/remotes/origin/pr/*', url: 'https://github.com/amruthapbhat/java-maven-junit-helloworld']]])
      
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'Maven'
     scannerHome = tool 'Sonar'
   }
         
   stage('Build') {
      // Run the maven build
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean install/)      
   }
   
   stage ('Sonar Analysis') {
   //Running Sonar Analysis
   withSonarQubeEnv {
   bat(/"${scannerHome}\bin\sonar-scanner" -Dsonar.projectKey=java-maven-junit-helloworld -Dsonar.sources=. /)
     }
   }  
    
    /*checkout([
        $class: 'GitSCM',
        branches: [[name: 'refs/heads/TestingMP']],
        userRemoteConfigs: [[
            name: 'origin',
            refspec: '+refs/pull/*:refs/remotes/origin/pr/*',
            //url: path
        ]],
        extensions: [
        [
            $class: 'PreBuildMerge',
            options: [
                fastForwardMode: 'NO_FF',
                mergeRemote: 'origin',
                mergeStrategy: 'MergeCommand.Strategy',
                mergeTarget: 'master'
            ]
        ],
        [
            $class: 'LocalBranch',
            localBranch: 'master'
        ]]
    ])
   // bat 'git log -n 10 --graph --pretty=oneline --abbrev-commit --all --decorate=full'
   
   // if (env.BRANCH_NAME == 'master') {
    //build 'master'
//}
    
}*/
    
  stage("Merging Pull Request") {
  withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'MyID', usernameVariable: 'GIT_USERNAME',
                    passwordVariable: 'GIT_PASSWORD']]) {

    bat("git tag -a some_tag -m 'Jenkins'")
    bat('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@<REPO> --tags')
}
}
}
