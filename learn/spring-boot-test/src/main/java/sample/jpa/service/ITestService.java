package sample.jpa.service;

import java.util.List;

import sample.jpa.domain.Test;

public interface ITestService {
	public List<Test> createAndSelect();
}