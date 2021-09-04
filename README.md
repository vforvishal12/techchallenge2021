# techchallenge2021
techchallenge2021-Full Stack Problem Statement 


# Problem Being Addressed and Solved
Paper based Baking forms are critical to Insurance Provider  and banks for managing their customer enrollment & maintenance processes.
Amazon Textract is a fully managed machine learning service that automatically extracts text and data from scanned documents.
Amazon Textract  process the response with the Amazon Textract parser library.



# Technology/Tool/stack
List of technology stack being used

Spring Boot – Integrator, data correction GUI, DB Storage & API provider
AWS S3 bucket – Image and PDF upload and storage
AWS Lambda – Lambda function for Textract API
AWS Textract – Extracts text and data from scanned documents 
AWS Dynamo DB – Store form data
AWS SNS – Job completion notification
React – Responsive UI 


# Solution Architecture
<img width="958" alt="image" src="https://user-images.githubusercontent.com/52970035/132089101-9d4a647c-8c3d-4c41-81d6-6a0563cfde8f.png">


![Flow drawio](https://user-images.githubusercontent.com/52970035/132089138-cdda8ad6-3fb6-4503-af64-fa8a235a76e5.png)


# Challenges Faced
Data scanning accuracy 
Skew correction
Data mapping of the scanned form with Database tables



# Future Enhancements
Batch Processing for Bulk Upload
Read & process dynamic form using NoSQL
Searchable PDF Creation
Realtime scanning with mobile device camera























