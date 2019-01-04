package com.example.demo;

public class TestDemo {

//	public class TestSimpleController {
//
//		/**
//		 * 用于注解Mock对象具体要注入到的那个对象
//		 * Controller类
//		 */
//		@InjectMocks
//		private UserController userController;
//
//		/**
//		 * 用于注解在需要被Mock的对象上
//		 * Controller类所需要引用的bean
//		 */
//		@Mock
//		private UserService userService;
//
//		private MockMvc mockMvc;
//
//		/**
//		 * 初始化,模拟加载mvc的web测试环境
//		 */
//		@Before
//		public void setUp() {
//			MockitoAnnotations.initMocks(this);
//			this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//		}
//
//		@Test
//		public void testGetUser() throws Exception {
//			User user = new User();
//			Mockito.when(userService.getUserByRealName("zhangsan")).thenReturn(user);
//			Assert.assertNotNull(user);
//
//			this.mockMvc.perform(MockMvcRequestBuilders.get("/test/getUser.json").param("realName", "zhangsan")
//					.accept(MediaType.APPLICATION_JSON))
//					.andDo(MockMvcResultHandlers.print())
//					.andExpect(MockMvcResultMatchers.status().isOk())
//					.andReturn();
//		}
}
