ALTER TABLE public.messages ALTER COLUMN date TYPE timestamp USING date::timestamp;