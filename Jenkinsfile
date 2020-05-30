pipeline{
    agent any
    tools {
        maven 'jenkins-maven'
    }
    stages{
        stage('Checkout'){
            steps{
                echo 'checkout'
//                 git branch: 'master', url:'https://github.com/LED1990/beerapp.git'
//                 sh 'echo checkout'
                checkout scm
                if(env.BRANCH_NAME == 'develop'){
                    echo 'branch name develop'
                }
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