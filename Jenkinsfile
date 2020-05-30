pipeline{
    agent any
    tools {
        maven 'jenkins-maven'
    }
    stages{
        stage('Checkout'){
            steps{
//                 checkout scm
                echo 'checkout'
//                 git branch: 'master', url:'https://github.com/LED1990/beerapp.git'
                sh 'echo checkout'
            }
        }
        stage('Test'){
                    steps{
                        echo 'testing...'
                        sh 'mvn test'
                    }
                }
        stage('Build'){
            steps{
                echo 'build...'
                sh 'mvn -DskipTests clean install'
            }
        }
        stage('Deploy'){
            steps{
                echo 'deploying...'
            }
        }
    }
}