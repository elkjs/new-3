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
        build 'Build'
      }
    }
    stage('smartbear') {
      steps {
        build 'smartbear'
      }
    }
    stage('xldeploy') {
       steps {
         build 'awsupload'
       }
    }
  }
}