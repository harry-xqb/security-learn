package com.security.learn.core.oauth.github.api;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 12:35
 **/
@Data
public class GithubInfo {

    private String login; //login":"harry-xqb",

    private String id;  // "id":42089387,

    private String node_id; // "node_id":"MDQ6VXNlcjQyMDg5Mzg3",

    private String type; // "type":"User"

    private String site_admin; // "site_admin":false

    private String name; // "name":"harry Xu",

    private String company;  // "company":null,

    private String blog; // "blog":"",

    private String location;

    private String email;

    private String hireable;

    private String bio;

    private Integer public_repos;

    private Integer public_gists;

    private Integer followers;

    private Integer following;

    private String created_at;

    private String updated_at;

    private String avatar_url; // "avatar_url":"https://avatars1.githubusercontent.com/u/42089387?v=4","

    private String gravatar_id;  // gravatar_id":"","

    private String url; // url":"https://api.github.com/users/harry-xqb",

    private String html_url; // "html_url":"https://github.com/harry-xqb",

    private String followers_url; // "followers_url":"https://api.github.com/users/harry-xqb/followers",

    private String following_url; // "following_url":"https://api.github.com/users/harry-xqb/following{/other_user}",

    private String gists_url; // "gists_url":"https://api.github.com/users/harry-xqb/gists{/gist_id}",

    private String starred_url; // "starred_url":"https://api.github.com/users/harry-xqb/starred{/owner}{/repo}",

    private String subscriptions_url; // "subscriptions_url":"https://api.github.com/users/harry-xqb/subscriptions",

    private String organizations_url; // "organizations_url":"https://api.github.com/users/harry-xqb/orgs",

    private String repos_url; // "repos_url":"https://api.github.com/users/harry-xqb/repos",

    private String events_url; // "events_url":"https://api.github.com/users/harry-xqb/events{/privacy}",

    private String received_events_url; // "received_events_url":"https://api.github.com/users/harry-xqb/received_events",

}