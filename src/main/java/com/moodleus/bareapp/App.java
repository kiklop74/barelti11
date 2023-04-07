package com.moodleus.bareapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.text.StringEscapeUtils;
import org.imsglobal.lti.BasicLTIUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Map<String, String> list() {
        String url = "https://training.atlas4learning.org/enrol/lti/tool.php?id=2";
        String method = "POST";
        String oauth_consumer_secret = "jyUZ0wAPvGTzoFfyuGRQh90yLV9xiqC4";
        String oauth_consumer_key = "somerandomvalue123";


        Map<String, String> postProp = new LinkedHashMap<>();
        postProp.put("oauth_version", "1.0");
        postProp.put("oauth_consumer_key", "somerandomvalue123");
        postProp.put("lti_message_type", "basic-lti-launch-request");
        postProp.put("lti_version", "LTI-1p0");
        postProp.put("oauth_signature_method", "HMAC-SHA1");
        postProp.put("oauth_callback", "about:blank");
        postProp.put("resource_link_id", "1");
        postProp.put("roles", "Student");
        postProp.put("ext_user_username", "user");
        postProp.put("tool_consumer_instance_guid", "put your site url here");
        postProp.put("tool_consumer_instance_name", "My glorious platform");
        postProp.put("tool_consumer_instance_description", "description");
        postProp.put("launch_presentation_document_target", "iframe");
        postProp.put("launch_presentation_return_url", "foo.url");
        postProp.put("lis_person_contact_email_primary", "user@example.com");
        postProp.put("ext_basiclti_submit", "Something");
        
        
        Map<String, String> nextProp = BasicLTIUtil.signProperties(
                postProp,
                url,
                method,
                oauth_consumer_key,
                oauth_consumer_secret,
                null,
                null,
                null,
                null,
                null
        );
        return nextProp;
    }
    
    public static void main( String[] args ) throws IOException
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\Darko Miletic\\Documents\\NetBeansProjects\\test.html"))) {
            writer.println("<html>");
            writer.println("<head><title>test</title></head>");
            writer.println("<body>");
            String action = "https://training.atlas4learning.org/enrol/lti/tool.php?id=2";
            writer.printf("<form id=\"ltiform\" name=\"ltiform\" action=\"%s\" method=\"post\">", action);
            Map<String, String> parameters = App.list();
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                writer.printf("<input name=\"%s\" value=\"%s\">", 
                        StringEscapeUtils.escapeHtml4(entry.getKey()), 
                        StringEscapeUtils.escapeHtml4(entry.getValue())
                );
                System.out.printf(
                        "name= %s value= %s\n", 
                        StringEscapeUtils.escapeHtml4(entry.getKey()), 
                        StringEscapeUtils.escapeHtml4(entry.getValue())
                );
            }
            writer.println("</form>");
            writer.println("<script>");
            writer.println("  window.onload = function() {");            
            writer.println("    document.ltiform.submit();");
            writer.println("  }");            
            writer.println("</script>");
            writer.println("</body>");
            writer.println("</html>");
            String oauth_consumer_key = "something";
            String oauth_consumer_secret = "jyUZ0wAPvGTzoFfyuGRQh90yLV9xiqC4";
            
            BasicLTIUtil.checkProperties(parameters, action, "POST", oauth_consumer_key, oauth_consumer_secret);
        }
    }
}
