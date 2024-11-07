pipeline {
    agent any

    tools {
        git 'Default Git' 
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'new', url: 'https://github.com/saloumdev123/phpproject.git'
            }
        }

        stage('Install Dependencies') {
            steps {
            
                bat 'composer install' 
            }
        }

        stage('Run Tests') {
            steps {
                dir('C:/Users/User-Hp/Documents/project/phpproject/HelloWorld.php') {
                bat 'php artisan test' 
             }
            }

        }

        stage('Static Analysis') {
            steps {
                bat 'vendor/bin/phpcs --standard=PSR2 app/' 
            }
        }

        stage('Build') {
            steps {
                // Laravel build steps for optimization
                bat 'php artisan cache:clear' // Clears the application cache
                bat 'php artisan config:clear' // Clears the configuration cache
                bat 'php artisan config:cache' // Rebuilds the configuration cache
                bat 'php artisan route:cache' // Caches the routes
                bat 'php artisan view:cache' // Caches the compiled views
                bat 'php artisan optimize' // Optional: Optimize Laravel
                bat 'npm install'          // Install frontend dependencies (if any)
                bat 'npm run prod'         // Compile assets for production
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/storage/logs/*.log', allowEmptyArchive: true
            junit 'tests/_output/*.xml'
        }
    }
}
