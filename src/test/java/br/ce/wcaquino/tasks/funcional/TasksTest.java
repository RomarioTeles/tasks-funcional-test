package br.ce.wcaquino.tasks.funcional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	private static final String IP_LOCAL = "localhost"; 
	
	public WebDriver acessarBrowser() throws MalformedURLException {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new RemoteWebDriver(new URL("http://"+IP_LOCAL+":4444/wd/hub"), capabilities);
		driver.navigate().to("http://"+IP_LOCAL+":8001/tasks");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarBrowser();
		
		try {
			//clicar no botão Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2040");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			assertEquals("Sucesso!", message);
			
		}finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException{
		
		WebDriver driver = acessarBrowser();
		
		try {
			//clicar no botão Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2040");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			assertEquals("Fill the task description", message);
			
		}finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException{
		
		WebDriver driver = acessarBrowser();
		
		try {
			//clicar no botão Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			assertEquals("Fill the due date", message);
			
		}finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException{
		
		WebDriver driver = acessarBrowser();
		
		try {
			//clicar no botão Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			
			assertEquals("Due date must not be in past", message);
			
		}finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
}
