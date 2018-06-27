package eagermodul;

import com.google.inject.name.Names;

import com.google.inject.AbstractModule;

public class EagerLoaderModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(StartUpService.class).asEagerSingleton();
	}

}
