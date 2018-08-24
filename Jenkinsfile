pipeline {
  agent any
  stages {
    stage('checkout') {
      steps {
        git(url: 'https://github.com/Sharmakartik/kartikapphello.git', branch: 'master', changelog: true, credentialsId: 'Seema12345', poll: true)
      }
    }
    stage('build') {
      steps {
        build 'test2'
      }
    }
    stage('sonar') {
      steps {
        build 'sonartest'
      }
    }
    stage('sonar1gate') {
      steps {
        build 'sonargate'
      }
    }
    stage('smartbear1.0') {
       steps {
         build 'readyapi'
       }
    }
  }
}