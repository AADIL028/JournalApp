package com.demo.main.services;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.demo.main.entity.User;

public class UserArgumentProvider implements ArgumentsProvider {

	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		return Stream.of(
				Arguments.of(User.builder().username("Test").password("test").build())
//				Arguments.of(User.builder().username("Test").password("").build()),
				);
	}

}
