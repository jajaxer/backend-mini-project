pipeline {

    agent any

    

    tools{

        maven "maven-3.9.8"

    }



    stages {

        stage('Git Clone') {

            steps {

               git branch: ‘main’, 

                   url: 'https://github.com/jajaxer/backend-mini-project.git'

            }

        }

        stage('Maven Build'){

            steps{

             sh 'mvn clean package'

            }

        }

      

    }

}
