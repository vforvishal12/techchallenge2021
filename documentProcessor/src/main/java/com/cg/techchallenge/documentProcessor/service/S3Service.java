package com.cg.techchallenge.documentProcessor.service;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Component
public class S3Service {

    S3Client s3;

    // Create the S3Client object.
    private S3Client getClient() {
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                "AKIAXSLCS2P6RR5P6TFM",
                "w3gKDhDAI+5bu6W/LwWJY6MBROdjWlaAL/P3Offe");
        Region region = Region.US_EAST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .build();

        return s3;
    }

    // Get the byte[] from this Amazon S3 object.
    public byte[] getObjectBytes(String bucketName, String keyName) {

        s3 = getClient();

        try {
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();
            return data;

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }

    // Returns the names of all images and data within an XML document.
    public String ListAllObjects(String bucketName) {

        s3 = getClient();


        List bucketItems = new ArrayList<String>();

        try {
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(bucketName)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();

            for (ListIterator iterVals = objects.listIterator(); iterVals.hasNext(); ) {
                S3Object myValue = (S3Object) iterVals.next();
                // Push the key to  the list.
                bucketItems.add(myValue.key());
            }

            return convertToString(toXml(bucketItems));

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return null;
    }


    // Places a PDF object into an Amazon S3 bucket.
    public String putObject(byte[] data, String bucketName, String objectKey) {

        s3 = getClient();

        try {
            PutObjectResponse response = s3.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(objectKey)
                            .build(),
                    RequestBody.fromBytes(data));

            return response.eTag();

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return "";
    }

    // Convert items into XML to pass back to the view.
    private Document toXml(List<String> itemList) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Start building the XML.
            Element root = doc.createElement("Docs");
            doc.appendChild(root);

            // Get the elements from the collection.
            int custCount = itemList.size();

            // Iterate through the collection.
            for (int index = 0; index < custCount; index++) {

                // Get the WorkItem object from the collection.
                String docName = itemList.get(index);

                Element item = doc.createElement("Doc");
                root.appendChild(item);

                // Set Key.
                Element id = doc.createElement("Key");
                id.appendChild(doc.createTextNode(docName));
                item.appendChild(id);
            }

            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertToString(Document xml) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(xml);
            transformer.transform(source, result);
            return result.getWriter().toString();

        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
