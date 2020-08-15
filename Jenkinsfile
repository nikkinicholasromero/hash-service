pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                bat 'mvn clean verify'
            }
        }
    }
}
