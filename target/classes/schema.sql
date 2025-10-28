-- Persona de ejemplo
CREATE TABLE IF NOT EXISTS person (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

-- Extensión para generar UUIDs (requiere permisos en la base de datos)
-- Nota: algunas plataformas gestionadas (Neon) no permiten crear extensiones.
-- Eliminamos la creación de extensión y NO usamos gen_random_uuid() por defecto.
-- La aplicación debe generar el UUID y enviarlo en INSERTs, o usar la función
-- del servidor si tu provider la habilita.
-- Tabla de municipalidades
CREATE TABLE IF NOT EXISTS municipalidades (
    -- Llave primaria
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    -- Identificadores únicos y oficiales
    nombre              VARCHAR(255) NOT NULL,
    ruc                 VARCHAR(11) UNIQUE, -- RUC de la entidad
    ubigeo              VARCHAR(6) UNIQUE NOT NULL, -- Código de Ubicación Geográfica (INEI)

    -- Clasificación de la municipalidad
    tipo                VARCHAR(20) NOT NULL DEFAULT 'DISTRITAL', -- PROVINCIAL, DISTRITAL, CENTRO POBLADO

    -- Ubicación geográfica (para consultas más sencillas)
    departamento        VARCHAR(100) NOT NULL,
    provincia           VARCHAR(100) NOT NULL,
    distrito           VARCHAR(100) NOT NULL,

    -- Datos de contacto y autoridad
    direccion           TEXT,
    telefono            VARCHAR(50),
    email               VARCHAR(255),
    website             VARCHAR(300),
    alcalde             VARCHAR(255), -- Nombre completo del alcalde actual

    -- Metadatos y auditoría
    activo              BOOLEAN DEFAULT true,
    created_at          TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    updated_at          TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

    -- Constraint para asegurar el tipo de municipalidad
    CONSTRAINT chk_tipo_municipalidad CHECK (tipo IN ('PROVINCIAL', 'DISTRITAL', 'CENTRO POBLADO'))
);

-- Índices para optimizar búsquedas comunes
CREATE INDEX IF NOT EXISTS idx_municipalidades_tipo ON municipalidades(tipo);
CREATE INDEX IF NOT EXISTS idx_municipalidades_ubigeo ON municipalidades(ubigeo);
CREATE INDEX IF NOT EXISTS idx_municipalidades_provincia ON municipalidades(provincia);

-- Comentarios sobre las columnas para mayor claridad
COMMENT ON COLUMN municipalidades.ubigeo IS 'Código de Ubicación Geográfica oficial del INEI (6 dígitos)';
COMMENT ON COLUMN municipalidades.tipo IS 'Tipo de municipalidad según la Ley Orgánica de Municipalidades.';
COMMENT ON COLUMN municipalidades.alcalde IS 'Nombre completo del alcalde en gestión.';

-- Tabla de configuración por tenant
CREATE TABLE IF NOT EXISTS configuracion_tenant (
  id UUID PRIMARY KEY,
  municipalidad_id UUID NOT NULL,
  nombre_sistema VARCHAR(200) DEFAULT 'Sistema de Control Patrimonial',
  logo_url VARCHAR(500),
  colores_tema JSONB DEFAULT '{"primary": "#1976d2", "secondary": "#dc004e"}'::jsonb,
  configuracion_reportes JSONB DEFAULT '{}'::jsonb,
  parametros_negocio JSONB DEFAULT '{}'::jsonb,
  timezone VARCHAR(50) DEFAULT 'America/Lima',
  moneda_default VARCHAR(3) DEFAULT 'PEN',
  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP DEFAULT NOW()
);
