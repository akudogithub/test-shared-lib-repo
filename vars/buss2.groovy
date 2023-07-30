def call(String repoUrl){
  pipeline{
    agent any
    stages{
        stage('git-clone'){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github-id', url: 'https://github.com/akudogithub/test-shared-lib-repo.git']])
            }
        }
        stage('cpu-check'){
            steps{
                sh 'lscpu'
            }
        }
        stage('system-check'){
            steps{
                sh 'lsblk'
            }
        }
        stage('ram-check'){
            steps{
                sh 'free -g'
                sh 'free -m'
            }
        }
        stage('regression-test'){
            parallel{
                stage('test-a'){
                    steps{
                        sh 'date'
                    }
                }
                stage('test-b'){
                    steps{
                        sh 'cal'
                    }
                }
            }
        }
        stage('check-user'){
            steps{
                sh 'lsblk'
            }
        }
    }
}
}

