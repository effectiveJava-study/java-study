package com.example;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.*;

public class Application {

    public final String token = "github_token";
    private GitHub github;

    public static void main(String[] args) {

        Application app = new Application();

        try {
            app.run();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    private void run() throws IOException {

        //연결
        connectGitApi();

        GHRepository ghRepository = github.getRepository("whiteship/live-study");

        //참여자 이름, 참여 횟수
        Map<String, Integer> participant = new HashMap<>();

        //모든 이슈를 가져온다.
        List<GHIssue> issues = ghRepository.getIssues(GHIssueState.ALL);

        //Find Issue
        for (GHIssue issue : issues){
            //각각의 Issue 에 존재하는 comment 들을 저장.
            List<GHIssueComment> commentList = issue.getComments();

            //혹시 모를 유저 중복을 제거하기 위한 Set
            Set<String> nameList = new HashSet<>();

            addParticipantInSet(commentList, nameList);

            //참여자 명단에서 비교한다.
            for (String s : nameList){
                hasParticipantInset(participant, s);
            }
        }

        printParticipantRate(participant);
    }

    private void hasParticipantInset(Map<String, Integer> participant, String s){
        if(!participant.containsKey(s)){
            participant.put(s, 1);
        } else {
            Integer integer = participant.get(s);
            participant.put(s, ++integer);
        }
    }

    private void addParticipantInSet(List<GHIssueComment> commentList, Set<String> name) throws IOException {
        for (GHIssueComment ghIssueComment : commentList){
            name.add(ghIssueComment.getUser().getLogin());
        }
    }

    private void printParticipantRate(Map<String, Integer> participant){
        participant.forEach((key, value)->{
            double percent = (double)(value*100) / 18;
            System.out.println(key + " : " + String.format("%.2f", percent) + "%");
        });
    }

    private void connectGitApi() throws IOException{
        github = new GitHubBuilder().withOAuthToken(token).build();
    }

}
