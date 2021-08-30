package home_work0;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class HttpUtilClient {
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private static final String HOST = "https://jsonplaceholder.typicode.com";
    private static final String USERS = "/users";
    private static final String USER_NAME = "?username=";
    private static final String POSTS = "/posts";
    private static final String COMMENTS = "/comments";
    private static final String TODOS = "/todos";

    @SneakyThrows
    public static User postRequest(User newUser) {
        String requestBody = GSON.toJson(newUser);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s", HOST, USERS, newUser)))
                .header("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    @SneakyThrows
    public static User putRequest(int userId, User user) {
        String requestBody = GSON.toJson(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s/%d", HOST, USERS, userId)))
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    @SneakyThrows
    public static User getRequest(int userId) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s/%d", HOST, USERS, userId)))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    @SneakyThrows
    public static List<User> getRequestAllUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s", HOST, USERS)))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
        return users;
    }

    @SneakyThrows
    public static User getRequestByUserId(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s/%d", HOST, USERS, id)))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), User.class);
    }

    @SneakyThrows
    public static List<User> getRequestUserByName(String name) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s%s%s", HOST, USERS, USER_NAME, name)))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(), new TypeToken<List<User>>() {
        }.getType());
    }


    @SneakyThrows
    public static int deleteRequest(int id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s/%d", HOST, USERS, id)))
                .DELETE()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    @SneakyThrows
    public static String getRequestCommentsOfLastPost(User user) {
        UserPost lastPost = getLastPost(user);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s/%d%s", HOST, POSTS, lastPost.getId(), COMMENTS)))
                .GET()
                .build();
        String fileName = "user-" + user.getId() + "-post-" + lastPost.getId() + "-comments.json";
        HttpResponse<Path> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(fileName)));
        return "File with comments : " + response.body();
    }

    @SneakyThrows
    private static UserPost getLastPost(User user) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format(format("%s%s/%d%s", HOST, USERS, user.getId(), POSTS))))
                .GET()
                .build();
        HttpResponse<String> responsePosts = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<UserPost> posts = GSON.fromJson(responsePosts.body(), new TypeToken<List<UserPost>>() {
        }.getType());
        return Collections.max(posts, Comparator.comparingInt(UserPost::getId));
    }

    @SneakyThrows
    public static List<UserTask> getRequestByTasks(User user) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(format("%s%s/%d%s", HOST, USERS, user.getId(), TODOS)))
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<UserTask> allTasks = GSON.fromJson(response.body(), new TypeToken<List<UserTask>>() {
        }.getType());
        return allTasks.stream()
                .filter(todo -> !todo.isCompleted())
                .collect(Collectors.toList());
    }

}
