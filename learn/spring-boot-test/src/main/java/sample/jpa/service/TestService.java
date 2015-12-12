package sample.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.jpa.domain.Test;
import sample.jpa.repository.JpaTestRepository;

@Service
public class TestService implements ITestService {
	@Autowired
	private JpaTestRepository testRepository;
	
	@Transactional
	public List<Test> createAndSelect(){
		Test test = new Test();
		test.setName("测试name1");
		testRepository.save(test);
		
		test = new Test();
		test.setName("测试name2");
		testRepository.save(test);
		
		return this.testRepository.findAll();
		
	}
}
