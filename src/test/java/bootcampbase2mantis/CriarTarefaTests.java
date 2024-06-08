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
                ExpectedConditions.elementToBeClickable(By.id("username"))
        );
        usernameField.sendKeys(username);

        //Busca botão de login e clica
        WebElement loginButton = driver.findElement(By.xpath("//*[@id='login-form']/fieldset/input[2]"));
        loginButton.click();

        //Busca campo de senha e insere senha
        WebElement passwordField = webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.id("password"))
        );
        passwordField.sendKeys(senha);

        //Busca botão de login e clica
        loginButton = driver.findElement(By.xpath("//*[@id='login-form']/fieldset/input[3]"));
        loginButton.click();
    }

    public void criaTarefa(String reprodubilidade, String prioridade, String resumo, String descricao, String passoAPasso, String informacaoAdicional ){
        //Busca botão de "Reporta issue" e clica
        WebElement criaTarefaBotao = webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sidebar']/ul/li[3]"))
        );
        criaTarefaBotao.click();

        //Busca elemento de categoria e seleciona opção 1 (categoria teste)
        WebElement categoryElement = webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By.id("category_id"))
        );
        Select categorySelect = new Select(categoryElement);
        categorySelect.selectByValue("1");


        //Busca elemento de reproducibilidade e seleciona opção "random"(50)

        WebElement reprodutibilidadeElement = driver.findElement(By.id("reproducibility"));
        Select reprodutibilidadeSelect = new Select(reprodutibilidadeElement);
        reprodutibilidadeSelect.selectByVisibleText(reprodubilidade);

        //Busca elemento de prioridade e seleciona opção "low"(20)

        WebElement prioridadeElement = driver.findElement(By.id("priority"));
        Select prioridadeSelect = new Select(prioridadeElement);
        prioridadeSelect.selectByVisibleText(prioridade);

        //Busca área de texto de Resumo e insere resumo

        WebElement resumoTextArea = driver.findElement(By.id("summary"));
        resumoTextArea.sendKeys(resumo);

        //Busca área de texto de Descrição e insere descrição

        WebElement descricaoTextArea = driver.findElement(By.id("description"));
        descricaoTextArea.sendKeys(descricao);

        //Busca área de texto de passos para reproduzir insere resumo passo a passo

        WebElement passosTextArea = driver.findElement(By.id("steps_to_reproduce"));
        passosTextArea.sendKeys(passoAPasso);

        //Busca área de texto de Informação adicional e insere informação

        WebElement infoAddTextArea = driver.findElement(By.id("additional_info"));
        infoAddTextArea.sendKeys(informacaoAdicional);

        // Obtém o diretório de trabalho atual e busca a imagem de erro
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        String resourcePath = "src\\main\\resources\\error.png";
        String erroImage = currentDir.resolve(resourcePath).toString();

        // Encontra elemento input file com css selector, pois o sistema usa dropzone.js e o elemento não está visível no DOM
        WebElement inputFile = driver.findElement(By.cssSelector("input[type=file]"));
        inputFile.sendKeys(erroImage);

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

    public boolean validarDadosCriados(String reprodubilidade, String prioridade, String resumo, String descricao, String passoAPasso, String informacaoAdicional){
        //pegar a informação dentro do campo: Frequência
        WebElement reprodubilidadeElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[6]/td[3]")));
        String reprodubilidadeElementText = reprodubilidadeElement.getText();

        //pegar a informação dentro do campo: Prioridade
        WebElement prioridadeElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[6]/td[1]")));
        String prioridadeElementText = prioridadeElement.getText();

        //pegar a informação dentro do campo: Resumo
        WebElement resumoElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[10]/td")));
        String resumoElementText = resumoElement.getText();

        //pegar a informação dentro do campo: Descrição
        WebElement descricaoElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[11]/td")));
        String descricaoElementText = descricaoElement.getText();

        //pegar a informação dentro do campo: Passo para Reproduzir
        WebElement passoAPassoElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[12]/td")));
        String passoAPassoElementText = passoAPassoElement.getText();

        //pegar a informação dentro do campo: Informação Adicionais
        WebElement informacaoAdicionalElement =  webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main-container\"]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tbody/tr[13]/td")));
        String informacaoAdicionalElementText = informacaoAdicionalElement.getText();

        //Comparar informação extraido com informação esperado pelo parametro
        boolean statusValidade = reprodubilidadeElementText.equals(reprodubilidade) && prioridadeElementText.equals(prioridade) && resumoElementText.contains(resumo) && descricaoElementText.contains(descricao) && passoAPassoElementText.equals(passoAPasso) && informacaoAdicionalElementText.contains(informacaoAdicional);

        return statusValidade;

    }

    @Test
    public void criarTarefaComSucesso(){
        driver.manage().window().maximize();
        login("grupoVerde2", "123456");
        String reprodubilidade ="aleatório";
        String prioridade = "baixa";
        String resumo = "Resumo para reporte de um problema com anexo";
        String descricao = "Teste de descrição para reporte de um problema";
        String passoAPasso =  "" +
                "1. Acessar o Mantis (https://mantis-prova.base2.com.br/)\n" +
                "2. Efetuar login (usuario: grupoXX, senha 123456, onde XX é o número do seu grupo)\n" +
                "3. Clicar em “Criar tarefa” no menu lateral\n" +
                "4. Selecionar uma categoria\n" +
                "5. Selecionar uma frequência";
        String informacaoAdicional = "Informação adicional para reporte de um problema";

        criaTarefa(reprodubilidade, prioridade, resumo, descricao, passoAPasso, informacaoAdicional);
        boolean statusCriacao = foiCriado();
        boolean statusDados = validarDadosCriados(reprodubilidade, prioridade, resumo, descricao, passoAPasso, informacaoAdicional);

        driver.quit();

        Assert.assertTrue(statusCriacao);
        Assert.assertTrue(statusDados);
    }
}
