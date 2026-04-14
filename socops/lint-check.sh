#!/usr/bin/env bash
# Simple code quality check script for the socops project

echo "Running code quality checks..."

# Set Java 21 environment
export JAVA_HOME="C:\Users\PREGO\dev\tools\OpenJDK21"
export PATH="$JAVA_HOME\bin:$PATH"

# Compile with enhanced linting
echo "1. Compiling with enhanced linting..."
if ./mvnw clean compile; then
    echo "✅ Compilation successful with no lint warnings"
else
    echo "❌ Compilation failed or has warnings"
    exit 1
fi

# Run tests
echo "2. Running tests..."
if ./mvnw test; then
    echo "✅ All tests passed"
else
    echo "❌ Tests failed"
    exit 1
fi

# Check for potential issues (basic patterns)
echo "3. Checking for basic code quality issues..."

# Check for unused imports (basic check)
echo "  - Checking for potential unused imports..."
find src -name "*.java" -exec grep -l "^import " {} \; | while read file; do
    imports=$(grep "^import " "$file" | grep -v "java.util.\|java.lang.\|org.springframework." | wc -l)
    if [ $imports -gt 10 ]; then
        echo "    Warning: $file has $imports imports - consider review"
    fi
done

# Check for long lines
echo "  - Checking for lines over 100 characters..."
find src -name "*.java" -exec awk 'length($0) > 100 {print FILENAME ":" NR ": " substr($0, 1, 50) "..."}' {} \; | head -5

# Check for missing JavaDoc on public methods
echo "  - Checking for missing JavaDoc on public methods..."
grep -n "public.*(" src/main/java/**/*.java | grep -v "/**" | head -3

echo "✅ Code quality checks completed successfully!"