/*
 * Copyright 2010 Adam Tybor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package roboguice.inject;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Indicates that a method observes the same method in context that the component
 * is instantiated in.
 *
 * When injecting a component into a context i.e. Activity or Service methods annotated
 * will be called when the corresponding method is executed on the activity.
 *
 * Very lightweight and useful for getting hooks into Activity methods
 * like onCreate(), onDestroy(), etc.
 *
 * Ensure that when using the annotation the signiture of method matches the signiture of
 * parent method exactly.  This will also not currently handle overloaded methods.
 *
 * You can easily extend the base RoboContext classes to provide the same functionality
 * for any methods.
 *
 * @code
 * {
 * public class MyBaseActivity extends RoboActivity implements SomethingHappened {
 *   protected void onSomethingHappened() {
 *     contextObservationManager.notify(this, "onSomethingHappened");
 *   }
 *
 *   protected void doSomething() {
 *     onSomethingHappened();
 *   }
 * }
 *
 * public class MyCustomActivity extends MyBaseActivity {
 *   @Inject CustomComponent component;
 *
 *   protected void myMethod() {
 *      doSomething();
 *   }
 * }
 *
 * public class CustomComponent {
 *   @ContextObserver
 *   public void onSomethingHappened() {
 *     // Handle something happened in this component
 *   }
 * }
 * }
 * @author Adam Tybor
 */
@Retention(RUNTIME)
@Target( { ElementType.METHOD })
@BindingAnnotation
public @interface ContextObserver {
}