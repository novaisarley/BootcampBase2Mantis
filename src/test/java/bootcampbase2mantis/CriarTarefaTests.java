package bootcampbase2mantis;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class CriarTarefaTests {
    private WebDriver driver = new ChromeDriver();
    private WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(60));

    public void login(String username, String senha){
        driver.get("https://mantis-prova.base2.com.br/");

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

    public void criaTarefa(){
        //Busca botão de "Reporta issue" e clica
        WebElement criaTarefaBotao = webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar']/ul/li[3]"))
        );
        criaTarefaBotao.click();

        //Busca elemento de categoria e seleciona opção 1 (categoria teste)
        WebElement categoryElement = webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='category_id']"))
        );
        Select categorySelect = new Select(categoryElement);
        categorySelect.selectByValue("1");

        // Obtém o diretório de trabalho atual e busca a imagem de erro
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        String resourcePath = "src\\main\\resources\\error.png";
        String erroImage = currentDir.resolve(resourcePath).toString();

        // Encontra elemento input file com css selector, pois o sistema usa dropzone.js e o elemento não está visível no DOM
        WebElement inputFile = driver.findElement(By.cssSelector("input[type=file]"));
        inputFile.sendKeys(erroImage);

        //Busca elemento de reproducibilidade e seleciona opção "random"(50)
        WebElement reprodutibilidadeElement = driver.findElement(By.xpath("//*[@id='reproducibility']"));
        Select reprodutibilidadeSelect = new Select(reprodutibilidadeElement);
        reprodutibilidadeSelect.selectByValue("50");

        //Busca elemento de prioridade e seleciona opção "low"(20)
        WebElement prioridadeElement = driver.findElement(By.xpath("//*[@id='priority']"));
        Select prioridadeSelect = new Select(prioridadeElement);
        prioridadeSelect.selectByValue("20");

        //Busca área de texto de Resumo e insere resumo
        WebElement resumoTextArea = driver.findElement(By.xpath("//*[@id='summary']"));
        resumoTextArea.sendKeys("Resumo para reporte de um problema com anexo");

        //Busca área de texto de Descrição e insere descrição
        WebElement descricaoTextArea = driver.findElement(By.xpath("//*[@id='description']"));
        descricaoTextArea.sendKeys("Teste de descrição para reporte de um problema");

        //Busca área de texto de passos para reproduzir insere resumo passo a passo
        WebElement passosTextArea = driver.findElement(By.xpath("//*[@id='steps_to_reproduce']"));
        passosTextArea.sendKeys("" +
                "1. Acessar o Mantis (https://mantis-prova.base2.com.br/)\n" +
                "2. Efetuar login (usuario: grupoXX, senha 123456, onde XX é o número do seu grupo)\n" +
                "3. Clicar em “Criar tarefa” no menu lateral\n" +
                "4. Selecionar uma categoria\n" +
                "5. Selecionar uma frequência");

        //Busca área de texto de Informação adicional e insere informação
        WebElement infoAddTextArea = driver.findElement(By.xpath("//*[@id='additional_info']"));
        infoAddTextArea.sendKeys("Informação adicional para reporte de um problema");


        //Busca botão de Submeter Problema e clica
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='report_bug_form']/div/div[2]/div[2]/input"));
        submitButton.click();
    }

    public boolean foiCriado(){
        String resposta = "";

        // Espera elemento que contém mensagem de sucesso e pega o texto contido nele
        WebElement mensagemElement = webDriverWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div/div/div[2]/p"))
        );
        resposta = mensagemElement.getText().toUpperCase();

        if (resposta.contains("SUCCESSFUL") || resposta.contains("SUCESSO")){
            return true;
        }
        return false;

    }

    @Test
    public void criarTarefaComSucesso(){
        driver.manage().window().maximize();
        login("grupoVerde2", "123456");
        criaTarefa();
        boolean statusCriacao = foiCriado();

        driver.quit();

        Assert.assertTrue(statusCriacao);
    }
}
