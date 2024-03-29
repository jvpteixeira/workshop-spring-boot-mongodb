package com.estudoapi.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudoapi.workshopmongo.domain.Post;
import com.estudoapi.workshopmongo.repository.PostRepository;
import com.estudoapi.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		if (!post.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		return post.get();
	}

	public List<Post> findByTitle(String txt){
		return repo.searchTitle(txt);
	}
	
	
	public List<Post> fullSearch(String txt, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000);
		return repo.fullSearch(txt,minDate,maxDate);
	}
	

}
