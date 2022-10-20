package com.konai.hsyang.konatoyfe;

import com.konai.hsyang.konatoyfe.testWebClient.GitHubCommit;
import com.konai.hsyang.konatoyfe.testWebClient.GitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@SpringBootApplication
public class KonatoyFeApplication {

	@Autowired
	WebClient.Builder webClientBuild;

	public static void main(String[] args) {
		SpringApplication.run(KonatoyFeApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();

			WebClient webClient = webClientBuild.baseUrl("https://api.github.com").build();

			Mono<GitHubRepository[]> reposMono = webClient.get().uri("/users/dev-hsyang/repos")
							.retrieve()
							.bodyToMono(GitHubRepository[].class);

			Mono<GitHubCommit[]> commitsMono = webClient.get().uri("/repos/dev-hsyang/konatoy-FE/commits")
							.retrieve()
							.bodyToMono(GitHubCommit[].class);
			// Mono, Flux는 Stream이다. 이 둘은 publisher인데, publisher특성상 subscribe를 하기 전에는 flow가 발생하지 않는다.
			// subscribe를 해야 실제 데이터가 흐르기 시작한다.


			reposMono.doOnSuccess(ra -> {
				Arrays.stream(ra).forEach(r -> {
					System.out.println("repo: " + r.getUrl());
				});
			}).subscribe(); // subscribe를 하여 data flow가 이루어지게 한다.

			commitsMono.doOnSuccess(ca -> {
				Arrays.stream(ca).forEach(c -> {
					System.out.println("commit: " + c.getSha());
				});
			}).subscribe();


			stopWatch.stop();
			System.out.println(stopWatch.prettyPrint());
		};
	}
}
