pipeline {
    agent any

    stages {
        stage('Verify Workspace') {
            steps {
                echo "Code is already checked out by Jenkins"
                dir('.') {
                    bat 'dir'
                }
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
