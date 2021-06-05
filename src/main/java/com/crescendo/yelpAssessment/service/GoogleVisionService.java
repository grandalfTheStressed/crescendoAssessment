package com.crescendo.yelpAssessment.service;

import com.crescendo.yelpAssessment.dto.ReviewDto;
import com.crescendo.yelpAssessment.dto.ReviewListDto;
import com.crescendo.yelpAssessment.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;

/*
{
  "requests": [
    {
      "image": {
        "content": "base64-encoded-image"
      },
      "features": [
        {
          "maxResults": 10,
          "type": "FACE_DETECTION"
        }
      ]
    }
  ]
}
*/

@Service
public class GoogleVisionService {

    private URL url = new URL("https://vision.googleapis.com/v1/images:annotate");

    private String googleApiKey = "Bearer " + "";
    public GoogleVisionService() throws MalformedURLException {
    }

    public void useGoogleVision(ReviewListDto list){

        for(ReviewDto review: list.getList()) {
            HttpURLConnection con = null;
            try {
                con = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                con.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", googleApiKey);
            con.setDoOutput(true);
            String jsonInputString = "{\n" +
                    "  \"requests\": [\n" +
                    "    {\n" +
                    "      \"image\": {\n" +
                    "        \"content\": \"" + getUserImgBase64(review.getUser()) +"\"\n" +
                    "      },\n" +
                    "      \"features\": [\n" +
                    "        {\n" +
                    "          \"maxResults\": 10,\n" +
                    "          \"type\": \"FACE_DETECTION\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                try {
                    JSONObject json = new JSONObject(response.toString()).getJSONArray("responses").getJSONObject(0).getJSONArray("faceAnnotations").getJSONObject(0);

                    String joyLikelihood = json.getString("joyLikelihood");
                    String surpriseLikelihood = json.getString("surpriseLikelihood");
                    String sorrowLikelihood = json.getString("sorrowLikelihood");
                    String angerLikelihood = json.getString("angerLikelihood");

                    review.getUser().setAngerLikelihood(angerLikelihood);
                    review.getUser().setJoyLikelihood(joyLikelihood);
                    review.getUser().setSurpriseLikelihood(surpriseLikelihood);
                    review.getUser().setSorrowLikelihood(sorrowLikelihood);
                }catch(JSONException e){
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserImgBase64(UserDto user){

        try {
            URL imageUrl = new URL(user.getImageUrl());
            URLConnection con = imageUrl.openConnection();
            InputStream is = con.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            Base64 base64 = new Base64();
            String base = base64.encodeToString(baos.toByteArray());

            return base;
        } catch (Exception e) {
        }

        return null;
    }
}
