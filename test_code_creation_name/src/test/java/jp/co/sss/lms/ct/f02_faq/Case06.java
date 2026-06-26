package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加

		//URLに飛ぶ		
		goTo("http://localhost:8080/lms/");
		//タイトルチェック		
		assertEquals("ログイン | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加

		String USER = "StudentAA01";
		String PASSWORD = "StudentAA001";

		// ログインID入力
		WebElement inputLoginId = webDriver.findElement(By.id("loginId"));
		inputLoginId.sendKeys(USER);

		// パスワード入力
		WebElement password = webDriver.findElement(By.id("password"));
		password.sendKeys(PASSWORD);

		// ログインボタン押下
		WebElement loginbtn = webDriver.findElement(By.className("btn-primary"));
		loginbtn.click();

		//タイトルチェック		
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加

		//「機能」押下
		WebElement functionClick = webDriver.findElement(By.className("dropdown-toggle"));
		functionClick.click();

		//「ヘルプ」押下
		WebElement helpClick = webDriver.findElement(By.linkText("ヘルプ"));
		helpClick.click();
		//タイトルチェック		
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		//click on link to open a new window
		webDriver.findElement(By.linkText("よくある質問")).click();
		//ウィンドウ情報の取得		
		Object[] windowHandles = webDriver.getWindowHandles().toArray();
		//新しく取得したウィンドウに切り替える
		webDriver.switchTo().window((String) windowHandles[1]);

		//遷移先タブのタイトルチェック		
		final WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.titleContains("よくある質問 | LMS"));

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// TODO ここに追加
		//【研修関係】カテゴリーを押下
		WebElement category = webDriver.findElement(By.linkText("【研修関係】"));
		category.click();

		WebElement Qclick = webDriver.findElement(By.id("question-h[${status.index}]"));
		assertEquals("Q.キャンセル料・途中退校について", Qclick.getText());

		scrollTo("500");
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// TODO ここに追加
		//質問を押下し回答を表示		
		WebElement Qclick = webDriver.findElement(By.id("question-h[${status.index}]"));
		Qclick.click();

		//回答をテスト
		WebElement Acheck = webDriver.findElement(By.id("answer-h[${status.index}]"));
		assertEquals("A. 受講者の退職や解雇等、やむを得ない事情による途中終了に関してなど、事情をお伺いした上で、協議という形を取らせて頂きます。 弊社営業担当までご相談下さい。",
				Acheck.getText());

		getEvidence(new Object() {
		});
	}

}
