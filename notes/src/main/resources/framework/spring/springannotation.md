# 一些注解

## ImportSelector接口
当我们需要导入某个类到Spring容器中去，但Spring恰好无法扫描到这个类，
而我们又无法修改这个类（jar包形式）。我们就可以通过`@import(xxx.class)`是将这个类导入到Spring容器中。
```java
// 不加任何注解或配置bean.xml，仅仅表示一个外部类，与spring无关的。
public class MySelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{Test.class.getName()};//数组中放入需要引入spring中的类名
    }
}
```

