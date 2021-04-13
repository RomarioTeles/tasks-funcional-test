package br.ce.wcaquino.tasks.funcional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	
	public WebDriver acessarBrowser() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		
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
			
			assertEquals("Success!", message);
			
		}finally {
			//fechar o browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
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
	public void naoDeveSalvarTarefaSemData() {
		
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
	public void naoDeveSalvarTarefaComDataPassada() {
		
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
