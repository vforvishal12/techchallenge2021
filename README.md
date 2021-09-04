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

![Flow drawio](https://user-images.githubusercontent.com/52970035/132089138-cdda8ad6-3fb6-4503-af64-fa8a235a76e5.png)


# Sample form

<img width="768" alt="image" src="https://user-images.githubusercontent.com/52970035/132089294-91d9e314-4461-4bca-b5c5-ca4f61edc412.png">

# Sample Filled form
<img width="768" alt="image" src="https://user-images.githubusercontent.com/52970035/132089345-3b83c87c-a458-491d-b0c0-61d54dcdd65e.png">

# Sample Filled Form extraction
<img width="137" alt="image" src="https://user-images.githubusercontent.com/52970035/132089377-c8d0bfb3-a39b-4a47-a0b8-be4270f5b245.png">


# Sample Filled Form extraction

<img width="375" alt="image" src="https://user-images.githubusercontent.com/52970035/132089384-7c2f693e-3881-40ad-b6ce-8de36515b863.png">
<img width="415" alt="image" src="https://user-images.githubusercontent.com/52970035/132089386-b0d57374-2725-4a73-a662-277bc1650e8c.png">





# Challenges Faced
Data scanning accuracy 
Skew correction
Data mapping of the scanned form with Database tables



# Future Enhancements
Batch Processing for Bulk Upload
Read & process dynamic form using NoSQL
Searchable PDF Creation
Realtime scanning with mobile device camera























