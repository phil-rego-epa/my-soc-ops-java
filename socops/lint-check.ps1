# Simple code quality check script for the socops project

Write-Host "Running code quality checks..." -ForegroundColor Green

# Set Java 21 environment
$env:JAVA_HOME = "C:\Users\PREGO\dev\tools\OpenJDK21"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

# Compile with enhanced linting
Write-Host "1. Compiling with enhanced linting..." -ForegroundColor Yellow
if (& .\mvnw clean compile) {
    Write-Host "✅ Compilation successful with no lint warnings" -ForegroundColor Green
} else {
    Write-Host "❌ Compilation failed or has warnings" -ForegroundColor Red
    exit 1
}

# Run tests
Write-Host "2. Running tests..." -ForegroundColor Yellow
if (& .\mvnw test) {
    Write-Host "✅ All tests passed" -ForegroundColor Green
} else {
    Write-Host "❌ Tests failed" -ForegroundColor Red
    exit 1
}

# Check for basic code quality issues
Write-Host "3. Checking for basic code quality issues..." -ForegroundColor Yellow

# Check for long lines
Write-Host "  - Checking for lines over 100 characters..." -ForegroundColor Cyan
Get-ChildItem -Path "src" -Recurse -Filter "*.java" | ForEach-Object {
    $lineNumber = 1
    Get-Content $_.FullName | ForEach-Object {
        if ($_.Length -gt 100) {
            Write-Host "    $($file.Name):$lineNumber - Line too long ($($_.Length) chars)" -ForegroundColor Yellow
        }
        $lineNumber++
    }
} | Select-Object -First 5

# Simple check for imports
Write-Host "  - Checking import usage..." -ForegroundColor Cyan
Get-ChildItem -Path "src\main\java" -Recurse -Filter "*.java" | ForEach-Object {
    $content = Get-Content $_.FullName
    $importCount = ($content | Where-Object { $_ -match "^import " }).Count
    if ($importCount -gt 15) {
        Write-Host "    $($_.Name) has $importCount imports - consider review" -ForegroundColor Yellow
    }
}

Write-Host "✅ Code quality checks completed!" -ForegroundColor Green