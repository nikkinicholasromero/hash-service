pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                mvn clean verify
            }
        }
    }
}
