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
import reactor.core.publisher.Flux;
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

			Flux<GitHubRepository> reposFlux = webClient.get().uri("/users/dev-hsyang/repos")
							.retrieve()
							.bodyToFlux(GitHubRepository.class);

			Flux<GitHubCommit> commitsFlux = webClient.get().uri("/repos/dev-hsyang/konatoy-FE/commits")
							.retrieve()
							.bodyToFlux(GitHubCommit.class);
			// Mono는 배열처럼 데이터를 묶음으로 받고, Flux는 배열안의 데이터를 한건씩을 ***stream으로 받는다.
			// Mono, Flux는 Stream이다. 이 둘은 publisher인데, publisher특성상 subscribe를 하기 전에는 flow가 발생하지 않는다.
			// subscribe를 해야 실제 데이터가 흐르기 시작한다.


			System.out.println("********************* MONO: STREAM에 있는 데이터묶음을 처리한다. ************************");
			// 각각 다른 stream이다. 먼저 응답이 오는순으로 결과가 sout 된다.
			// mono.doOnSuccess: stream에 있는 mono(데이터묶음)에 접근
			reposMono.doOnSuccess(ra -> {
				Arrays.stream(ra).forEach(r -> {
					System.out.println("repo: " + r.getUrl());
				});
			}).subscribe(); // subscribe를 하여 data flow가 이루어지게 한다.

			// 각각 다른 stream. 비동기식이다 async하다. non-blocking하다.
			commitsMono.doOnSuccess(ca -> {
				Arrays.stream(ca).forEach(c -> {
					System.out.println("commit: " + c.getSha());
				});
			}).subscribe();


			System.out.println("*********************** FLUX: STREAM에 있는 데이터에 하나씩 접근한다. *************************");
			// flux.doOnNext: stream에 들어있는 개별데이터에 하나씩 접근.
			reposFlux.doOnNext(r -> {
				System.out.println("repo: " + r.getUrl());
			}).subscribe(); // subscribe해줘야 data가 흐른다.

			commitsFlux.doOnNext(c -> {
				System.out.println("commit: " + c.getSha());
			}).subscribe();


			stopWatch.stop();
			System.out.println(stopWatch.prettyPrint());
		};
	}
}
