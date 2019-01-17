pipeline {
  agent any
  tool name: 'java', type: 'jdk'
  tool name: 'maven', type: 'maven'
  stages {
        stage('Build') { 
            steps {
                bat 'mvn -B -DskipTests clean package' 
            }
        }
    }

}
