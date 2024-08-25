import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;

@SpringBootApplication
@RestController
public class tejaswiniApi1 {

    @GetMapping("/api/endpoint")
    public ResponseEntity<String> getEndpoint() {
        return new ResponseEntity<>("GET_SUCCESS", HttpStatus.OK);
    }

    @PostMapping("/api/endpoint")
    public ResponseEntity<PostResponse> postEndpoint(@RequestBody PostRequest request) {
        if (!isValidRequest(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String highestLowercaseAlphabet = getHighestLowercaseAlphabet(request.getAlphabets());

        PostResponse response = new PostResponse(
                "success",
                request.getUserId(),
                request.getCollegeEmailId(),
                request.getCollegeRollNumber(),
                request.getNumbers(),
                request.getAlphabets(),
                new String[]{highestLowercaseAlphabet}
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private boolean isValidRequest(PostRequest request) {
        return request.getUserId() != null &&
                request.getCollegeEmailId() != null &&
                request.getCollegeRollNumber() != null &&
                request.getNumbers() != null &&
                request.getAlphabets() != null;
    }

    private String getHighestLowercaseAlphabet(String[] alphabets) {
        return Arrays.stream(alphabets)
                .max(Comparator.comparingInt(String::toLowerCase))
                .orElse("");
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}

class PostRequest {
    private Integer userId;
    private String collegeEmailId;
    private String collegeRollNumber;
    private Integer[] numbers;
    private String[] alphabets;

    // getters and setters
}

class PostResponse {
    private String status;
    private Integer userId;
    private String collegeEmailId;
    private String collegeRollNumber;
    private Integer[] numbers;
    private String[] alphabets;
    private String[] highestLowercaseAlphabet;

    // getters and setters
}
