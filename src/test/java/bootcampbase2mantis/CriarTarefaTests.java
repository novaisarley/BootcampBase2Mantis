package bootcampbase2mantis;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CriarTarefaTests {
    public WebDriver driver = new ChromeDriver();

    public void login(String username, String senha){
        driver.get("https://mantis-prova.base2.com.br/");

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(60));

        //Busca campo de usuario e insere username
        WebElement usernameField = webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='username']"))
        );
        usernameField.sendKeys(username);

        //Busca botão de login e clica
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login-form']/fieldset/input[2]"));
        loginButton.click();

        //Busca campo de senha e insere senha
        WebElement passwordField = webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='password']"))
        );
        passwordField.sendKeys(senha);

        //Busca botão de login e clica
        loginButton = driver.findElement(By.xpath("//*[@id='login-form']/fieldset/input[3]"));
        loginButton.click();
    }

    @Test
    public void criarTarefaComSucesso(){
        driver.manage().window().maximize();
        login("grupoVerde2", "123456");

        driver.quit();

    }
}
