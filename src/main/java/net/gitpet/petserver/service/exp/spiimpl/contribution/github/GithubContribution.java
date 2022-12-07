package net.gitpet.petserver.service.exp.spiimpl.contribution.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.gitpet.petserver.service.exp.contribution.spi.Contribution;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public final class GithubContribution implements Contribution {

    private final String GITHUB_API_URL;
    private final ObjectMapper OBJECT_MAPPER;

    GithubContribution(){
        GITHUB_API_URL = "https://api.github.com/graphql";
        OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    public int todayContributions(String name) {
        String currentDayQueryString = generateQuery(name, LocalDate.now());
        try {
            return getCount(currentDayQueryString, GithubSecret.TOKEN.VALUE);
        }catch(IOException IOE){
            throw new IllegalStateException();
        }
    }

    private int getCount(String query, String token) throws IOException {
        URL url = new URL(GITHUB_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try(AutoCloseable autoCloseable = connection::disconnect){
            connection.setRequestMethod(HttpMethod.POST.name());
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Authorization", token);
            connection.setDoOutput(true);
            try(OutputStream outputStream = connection.getOutputStream()){
                outputStream.write(query.getBytes(StandardCharsets.UTF_8));
                try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))){
                    GithubData data = OBJECT_MAPPER.readValue(br.readLine(), GithubData.class);
                    return data.getCommitCount();
                }
            }
        }catch(Exception e){
            throw new IllegalStateException("Fail to get contribution count by github");
        }
    }

    private String generateQuery(String name, LocalDate localDate){
        String date = localDate.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("\"query\" : \"query($login: String!, $from: DateTime, $to: DateTime){ rateLimit{cost remaining limit nodeCount} user(login: $login){ name contributionsCollection(from : $from, to: $to){ totalCommitContributions } } }\", ");
        sb.append("\"variables\" : {\"login\" : ");
        sb.append("\"");
        sb.append(name);
        sb.append("\",");
        sb.append("\"from\" : ");
        sb.append("\"");
        sb.append(date);
        sb.append("T00:00:00Z");
        sb.append("\",");
        sb.append("\"to\" : ");
        sb.append("\"");
        sb.append(date);
        sb.append("T24:00:00Z\"");
        sb.append("}\n");
        sb.append("}\n");
        return sb.toString();
    }

}
