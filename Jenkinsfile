pipeline {
  agent any
  stages {
    stage('checkout project') {
      steps {
        checkout scm
      }
    }

    stage('build') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('report') {
      parallel {
        stage('archive') {
          steps {
            archiveArtifacts 'target/*.jar'
          }
        }

        stage('junit') {
          steps {
            junit '**/target/surefire-reports/TEST-*.xml'
          }
        }

      }
    }

  }
}