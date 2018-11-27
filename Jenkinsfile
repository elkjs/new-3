pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/Sharmakartik/kartikapphello.git', branch: 'master', changelog: true, credentialsId: 'Seema12345', poll: true)
      }
    }
    stage('Build') {
      steps {
        build 'Build'
      }
    }
    stage('Selenium') {
          steps {
            build 'selenium1.0'
          }
        }
    stage('Cucumber') {
          steps {
            build 'cucumber'
          }
        }
    stage('smartbear') {
      steps {
        build 'smartbear'
      }
    }
    stage('Sonarqube') {
          steps {
            build 'sonarcube'
          }
        }
    stage('Sonarqube-fail') {
          steps {
                 build 'sonarcubefail'
            }
         }
    stage('xldeploy') {
       steps {
         build 'awsupload'
       }
    }
  }
}