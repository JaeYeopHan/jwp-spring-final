# 자바 웹 프로그래밍 최종 점검
## 최종 점검 문서 
* JWP Basic 최종 점검.pdf 문서를 참고해 최종 점검 진행

## 질문에 대한 답변
#### 2. Tomcat 서버를 시작 과정을 설명하시오.
`WebApplicationInitializer`라는 인터페이스를 `implements`하고 있는 클래스가 실행된다.
이 인터페이스에는 `onStartup`이라는 메소드가 존재하여 이 메소드에서 `ApplicationContext`를 생성하게 된다
현재 이 프로젝트는 `annotation`를 기반으로 configuration이 정의되어 있기 때문에
`AnnotationConfigWebApplicationContext` 클래스를 인스턴스로 생성하여
각종 `config`들을 `register`한다.
이 프로젝트에서는 `AppConfig`, `WebMvcConfig` 두 클래스를 기반으로
각각 appContext, webContext라는 이름의 context를 생성헸으며,
webContext는 appContext를 부모로 하고 있다.

각 config 클래스는 `@Configuration` 어노테이션이 추가되어 있으며
각각에서 정의되어 있는 `@ComponentScan` 어노테이션에서 `basepackage` 파라미터로 받은 패키지에 대해 bean을 생성한다.
`@Component` 또는 `@Service` 등등 bean과 관련된 어노테이션이 붙어있는 각각의 클래스들을 bean으로 생성하기 전
해당 클래스와 의존관계를 맺고 있는 bean들을 우선적으로 파악한 뒤, 순서에 맞게 bean을 생성하고 주입한다.
각각의 bean들은 `@PostConstruct`와 같은 bean의 라이프사이클을 관리할 수 있는 어노테이션(xml로도 가능하다.)을 사용할 수 있으며,
이러한 어노테이션에 해당하는 작업도 이 때 함께 진행된다.

스프링 컨테이너에 bean을 등록하는 방법은 위에서 언급한 것처럼 bean으로 관리할 클래스에 어노테이션 또는 xml로 설정하는 방법도 있고,
config 클래스에서 `@Bean` 어노테이션을 통해 등록할 수 있다.
이 방법은 클래스 코드에 어노테이션을 추가할 수 없는 외부 라이브러리를 bean으로 등록할 때 사용된다.
이 방식으로 등록된 bean 역시 이 때 생성되고 스프링 컨테이너에 의해 관리된다.


#### 3. http://localhost:8080 으로 요청했을 때의 과정을 설명하시오.
(스프링 컨테이너의 초기화 작업이 완료되어 bean이 모두 생성되었다는 것을 가정한다.)
클라이언트(브라우저)에서 해당 url로 요청을 보내게 되면 서버 측에서는 `/`라는 요청으로 인식하여 해당하는 resource를 응답한다.
요청을 최초로 받는 부분은 `Controller`이며 요청받은 path와 Http method에 mapping 해둔 작업을 처리한다.
이 프로젝트에서는 해당하는 컨트롤러가 `HomeController`이며 `home()`이라는 메소드가 mapping되어 있다.
`/`에서는 질문 목록을 보여줘야 하므로 데이터베이스에 접근할 수 있는 `questionDao`를 통해 질문 목록을 가져온다.
그리고 클라이언트에게 반환하려는 view에 질문 목록을 추가하여 반환한다.

#### 6. QuestionController가 multi thread에서 문제가 되는 이유를 설명하시오.
`question`, `answers` 객체가 필드에 존재하는데 `QuestionController`는 bean으로 등록되어 초기에 한 번 생성되고
계속 하나의 인스턴스가 재사용된다. 이럴 때 여러 개의 스레드가 접근하는 경우 필드 멤버를 공유해서 사용하게 되는데,
그렇게 되면 각 스레드마다 원하는 데이터 값이 나오지 않을 수 있다.
